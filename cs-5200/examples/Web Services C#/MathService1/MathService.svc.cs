using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace MathService1
{
    public class MathService : IMathService
    {
        public Int32 Add(Int32 num1, Int32 num2)
        {
            return num1 + num2;
        }

        public Int32 Subtract(Int32 num1, Int32 num2)
        {
            return num1 - num2;
        }

        public Int32 Multiply(Int32 num1, Int32 num2)
        {
            return num1 * num2;
        }

        public Int32 Divide(Int32 num1, Int32 num2)
        {
            return num1 / num2;
        }

    }
}
