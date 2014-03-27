using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using AgentCommon;

namespace BrilliantStudent
{
  class JoinGameExecutionStrategy : ExecutionStrategy 
  {
    public JoinGameExecutionStrategy(int conversationId)
      : base(conversationId) { }
    protected override void Execute()
    {
      
    }
  }
}
