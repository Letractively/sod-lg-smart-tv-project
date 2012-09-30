using System;
using System.Collections;
using System.Linq;
using System.Text;
using System.Xml.Linq;

namespace WebSocketTest
{
    class Packet
    {
        const string XmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";

        /*
         * 클라이언트에서 접속을 요청하는 패킷 시그니쳐
        */
        public const uint REQUEST_ACCEPT = 0xFF000010;

        /*
         * 서버에서 접속을 승인했음을 알려주는 패킷 시그니쳐
        */
        public const uint RESPONSE_ACCEPT = 0xFF000011;

        /*
         * 서버가 존재하는지 알기 위해 클라이언트에서 보내는 패킷 시그니쳐
        */
        public const uint REQUEST_PING = 0xFF000020;

        /*
         * 클라이언트에서 요청한 핑에 응답하는 패킷 시그니쳐
        */
        public const uint RESPONSE_PING = 0xFF000021;

        /**
         * 스마트TV에서 스마트폰과 접속이 완료되고난 뒤에 스마트폰에 서비스를 실행하기위한 파일이 있는지를 확인한다. 이 때, 파일이 있는지를
         * 확인하기 위해 서비스의 명칭을 보내기 위한 패킷의 시그니쳐
         */
        public const uint RESPONSE_SERVICE_NAME = 0xFF000000;

        /**
         * 스마트TV에서 스마트폰의 접속상태가 유효한지를 체크하기위해 보내는 패킷의 시그니쳐
         */
        public const uint REQUEST_CLIENT_ALIVE = 0xFF000001;

        /**
         * 스마트TV로부터 접속상태 확인 패킷을 받은 후, 접속상태가 양호하다는 응답 패킷의 시그니쳐
         */
        public const uint RESPONSE_CLIENT_ALIVE = 0xFF000002;

        /**
         * 스마트TV로부터 서비스명칭을 받은 후, 서비스를 실행시키기위한 파일이 있는지를 확인하고 파일이 없을때 보내는 패킷의 시그니쳐
         */
        public const uint REQUEST_SERVICE_DATA = 0xFF000003;

        /**
         * 스마트폰으로부터 서비스가 없다는 패킷을 받은 후, 서비스 실행과 관련된 파일을 보내주는 패킷의 시그니쳐
         */
        public const uint RESPONSE_SERVICE_DATA = 0xFF000004;

        protected Queue dataset = new Queue();

        /**
         * 개발자가 지정한 시그니쳐로 구분하여 패킷의 용도가 결정된다. 0xff000000~0xff000004는 시스템에서 할당한
         * 시그니쳐이므로 개발자는 사용하면 안된다.
         */
        public int Signiture = 0;

        public int ElementCount { get { return dataset.Count; } }

        public bool Push(object o)
        {
            var t = o.GetType().FullName;
            switch (t)
            {
                case "System.Int32":
                case "System.Int64":
                case "System.Single":
                case "System.Double":
                case "System.String":
                case "System.Byte[]":
                    dataset.Enqueue(o);
                    return true;
                default:
                    return false;
            }
        }

        public string GetTypeTag(Type t)
        {
            switch (t.FullName)
            {
                case "System.Int32": return "int";
                case "System.Int64": return "long";
                case "System.Single": return "float";
                case "System.Double": return "double";
                case "System.String": return "string";
                case "System.Byte[]": return "bytearray";
                default: return null;
            }
        }

        public object Pop()
        {
            if (dataset.Count == 0)
                return null;
            return dataset.Dequeue();
        }

        public object Peek()
        {
            if (dataset.Count == 0)
                return null;
            return dataset.Peek();
        }

        public void Clear()
        {
            Signiture = 0;
            dataset.Clear();
        }

        public string ToXml()
        {
            var rv = new XElement("Packet");
            rv.SetAttributeValue("signiture", Signiture);
            foreach (var item in dataset)
            {
                object item2;
                if (item is byte[])
                    item2 = Convert.ToBase64String((byte[])item);
                else
                    item2 = item;

                var element = new XElement("Item", item2);
                element.SetAttributeValue("type", GetTypeTag(item.GetType()));
                rv.Add(element);
            }
            return XmlHeader + rv.ToString();
        }

        object ToObject(XElement xml)
        {
            var type = xml.Attribute("type");
            switch (type.Value)
            {
                case "int":
                    return Int32.Parse(xml.Value);
                case "long":
                    return Int64.Parse(xml.Value);
                case "float":
                    return Single.Parse(xml.Value);
                case "double":
                    return Double.Parse(xml.Value);
                case "string":
                    return xml.Value;
                case "bytearray":
                    return Convert.FromBase64String(xml.Value);
                default:
                    throw new ArgumentException();
            }
        }

        public void FromXml(string xmlstr)
        {
            var xml = XElement.Parse(xmlstr);
            foreach(var item in xml.Descendants("Item"))
                dataset.Enqueue(ToObject(item));            
        }
    }
}
