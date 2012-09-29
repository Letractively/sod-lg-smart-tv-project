using System;
using System.Collections;
using System.Linq;
using System.Text;
using System.Net;
using System.Net.Sockets;

namespace WebSocketTest
{
    class Transceiver : IDisposable
    {
        public const int TransferUnit = 0x10000;//64kb

        byte[] recvbuf;
        int recvport;
        EndPoint dest;
        Socket conn;
        
        static Socket CreateUdpSocket()
        {
            return new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);
        }

        static IPAddress GetLocalIP()
        {
            var iplist = Dns.GetHostAddresses(Dns.GetHostName());
            foreach (var item in iplist)
                if (item.AddressFamily == AddressFamily.InterNetwork)
                    return item;
            return null;
        }

        public Transceiver(EndPoint dest, int recvport)
        {
            this.dest = dest;
            this.recvport = recvport;
            recvbuf = new byte[TransferUnit];
            conn = CreateUdpSocket();
            if (recvport != 0)
                conn.Bind(new IPEndPoint(GetLocalIP(), recvport)); 
        }

        public void Dispose()
        {
            if (conn != null)
            {
                conn.Dispose();
                conn = null;
            }
        }

        ~Transceiver()
        {
            Dispose();
        }

        public bool Send(Packet p)
        {
            if (dest == null)
                throw new InvalidOperationException();

            try
            {
                var data = Encoding.UTF8.GetBytes(p.ToXml());
                conn.SendTo(data, dest);
                return true;
            }
            catch (Exception ex)
            {
                return false;
            }
        }

        public EndPoint Receive(ref Packet p)
        {
            if(recvport == 0)
                throw new InvalidOperationException();

            EndPoint host = new IPEndPoint(IPAddress.Any, recvport);
            int len = conn.ReceiveFrom(recvbuf, ref host);
            var xmlstr = Encoding.UTF8.GetString(recvbuf, 0, len);
            p.FromXml(xmlstr);
            return host;
        }
    }
}
