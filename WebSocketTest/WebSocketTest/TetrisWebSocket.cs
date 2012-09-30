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

    class TetrisWebSocket
    {
        WebSocketSession conn = null;
        const int Port_WebSocket = 2012;
        const int Port_Transceiver = 2013;
        bool isRunning;

        public void Run()
        {
            //websocket init
            Console.WriteLine("tetris server is starting... port:{0}", Port_WebSocket);
            var listener = new WebSocketServer();
            listener.NewMessageReceived += listener_NewMessageReceived;
            listener.NewSessionConnected += listener_NewSessionConnected;

            if (!listener.Setup(Port_WebSocket))
            {
                Console.WriteLine("failed to initialize.");
                return;
            }

            if (!listener.Start())
            {
                Console.WriteLine("failed to start.");
                return;
            }

            //transceiver init
            Console.WriteLine("transceiver is starting... port:{0}", Port_Transceiver);
            var tr = new Transceiver(null, 2013);

            isRunning = true;
            Thread th = new Thread(() =>
            {
                Packet p = new Packet();
                while (isRunning)
                {
                    p.Clear();
                    tr.Receive(ref p);

                    Console.WriteLine("received a packet: {0}", p.Peek());

                    if (conn != null)
                    {
                        try
                        {
                            conn.Send((string)p.Pop());
                        }
#pragma warning disable 0168
                        catch (Exception ex)
                        {
                            //conn = null;
                        }
#pragma warning restore 0168
                    }
                }
                tr.Dispose();
            });
            th.Start();

            Console.WriteLine("press enter to terminate server.");
            Console.ReadLine();

            isRunning = false;
            listener.Stop();
            Console.WriteLine("terminating server...");
        }

        void listener_NewSessionConnected(WebSocketSession session)
        {
            Console.WriteLine("websocket is bound");
            if (conn != null)
                conn.Close();
            conn = session;
        }

        void listener_NewMessageReceived(WebSocketSession session, string e)
        {
            //do not need to handle.
        }

        static IPAddress GetLocalIP()
        {
            var iplist = Dns.GetHostAddresses(Dns.GetHostName());
            foreach (var item in iplist)
                if (item.AddressFamily == AddressFamily.InterNetwork)
                    return item;
            return null;
        }

    }
}