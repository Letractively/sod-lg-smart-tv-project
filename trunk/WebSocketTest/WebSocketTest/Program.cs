using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.Net.Sockets;
using SuperWebSocket;
using System.Threading;
using System.Threading.Tasks;

namespace WebSocketTest
{
    class Program
    {
        const int ServerPort = 2012;
        static ConsoleKey lastkey = ConsoleKey.Enter;
        static bool iskeyhandled = true;

        static void Main(string[] args)
        {
            try
            {
                TestWebSocket();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
                Console.ReadLine();
            }
        }

        static IPAddress GetLocalIP()
        {
            var iplist = Dns.GetHostAddresses(Dns.GetHostName());
            foreach (var item in iplist)
                if (item.AddressFamily == AddressFamily.InterNetwork)
                    return item;
            return null;
        }

        static void TestTransceiver()
        {
            Thread t = new Thread(() =>
            {
                using (var listener = new Transceiver(null, ServerPort))
                {
                    var p = new Packet();
                    listener.Receive(ref p);
                    Console.WriteLine("received: {0}",p.Pop());
                }
            });
            t.Start();

            using (var sender = new Transceiver(new IPEndPoint(GetLocalIP(), ServerPort), 0))
            {
                Thread.Sleep(500);
                var p = new Packet();
                p.Push("transceiver test");
                sender.Send(p);                
            }

            Console.WriteLine("press enter to finish.");
            Console.ReadLine();
        }

        static void TestWebSocket()
        {
            Console.WriteLine("websocket server is starting... port:{0}", ServerPort);
            var listener = new WebSocketServer();
            
            if (!listener.Setup(ServerPort))
            {
                Console.WriteLine("failed to initialize.");
                return;
            }

            listener.NewMessageReceived += new SessionEventHandler<WebSocketSession,string>(listener_NewMessageReceived);

            if (!listener.Start())
            {
                Console.WriteLine("failed to start.");
                return;
            }

            listener.NewSessionConnected += (session) =>
            {
                Console.WriteLine("new session is connected.");

                Thread th = new Thread(() =>
                {
                    while (true)
                    {
                        if(iskeyhandled)
                            continue;
    
                        iskeyhandled = true;
                        if(lastkey == ConsoleKey.Escape)
                            break;

                        Console.WriteLine("handling key" + lastkey);
                        switch(lastkey){
                            case ConsoleKey.UpArrow:                                
                                session.Send(38.ToString()); break;
                            case ConsoleKey.DownArrow:
                                session.Send(40.ToString()); break;
                            case ConsoleKey.LeftArrow:
                                session.Send(37.ToString()); break;
                            case ConsoleKey.RightArrow:
                                session.Send(39.ToString()); break;
                            default:
                                break;                                
                        }
                    }

                    session.Close();
                });

                th.Start();
            };

            Console.WriteLine("press escape to terminate server.");

            while (true)
            {
                var key = Console.ReadKey();
                Console.WriteLine("key event: " + key.Key);
                lastkey = key.Key;
                iskeyhandled = false;
                if (key.Key == ConsoleKey.Escape)
                    break;
            }

            listener.Stop();
            Console.WriteLine("terminating server...");
        }

        static void listener_NewMessageReceived(WebSocketSession session, string msg)
        {
            session.Send("(response) " + msg);
        }
    }
}
