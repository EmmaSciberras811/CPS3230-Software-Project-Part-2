package larva;


import main.AlertSystem;
import main.Alerts;


import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_AlertSys0 implements _callable{

public static PrintWriter pw; 
public static _cls_AlertSys0 root;

public static LinkedHashMap<_cls_AlertSys0,_cls_AlertSys0> _cls_AlertSys0_instances = new LinkedHashMap<_cls_AlertSys0,_cls_AlertSys0>();
static{
try{
RunningClock.start();
pw = new PrintWriter("C:\\Users\\User\\workspace\\testrv/src/output_AlertSys.txt");

root = new _cls_AlertSys0();
_cls_AlertSys0_instances.put(root, root);
  root.initialisation();
}catch(Exception ex)
{ex.printStackTrace();}
}

_cls_AlertSys0 parent; //to remain null - this class does not have a parent!
int no_automata = 1;
 public int noOfAlerts =0 ;

public static void initialize(){}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_AlertSys0() {
}

public void initialisation() {
}

public static _cls_AlertSys0 _get_cls_AlertSys0_inst() { synchronized(_cls_AlertSys0_instances){
 return root;
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_AlertSys0))
{return true;}
else
{return false;}
}

public int hashCode() {
return 0;
}

public void _call(String _info, int... _event){
synchronized(_cls_AlertSys0_instances){
_performLogic_MonitorAlertListS(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_AlertSys0[] a = new _cls_AlertSys0[1];
synchronized(_cls_AlertSys0_instances){
a = _cls_AlertSys0_instances.keySet().toArray(a);}
for (_cls_AlertSys0 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_AlertSys0_instances){
_cls_AlertSys0_instances.remove(this);}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}

int _state_id_MonitorAlertListS = 198;

public void _performLogic_MonitorAlertListS(String _info, int... _event) {

_cls_AlertSys0.pw.println("[MonitorAlertListS]AUTOMATON::> MonitorAlertListS("+") STATE::>"+ _string_MonitorAlertListS(_state_id_MonitorAlertListS, 0));
_cls_AlertSys0.pw.flush();

if (0==1){}
else if (_state_id_MonitorAlertListS==198){
		if (1==0){}
		else if ((_occurredEvent(_event,380/*delAllAlerts*/)) && (noOfAlerts ==0 )){
		_cls_AlertSys0.pw .println ("Bad Delete!!!");

		_state_id_MonitorAlertListS = 197;//moving to state badDelete
		_goto_MonitorAlertListS(_info);
		}
		else if ((_occurredEvent(_event,378/*addAlert*/)) && (noOfAlerts ==5 )){
		noOfAlerts ++;

		_state_id_MonitorAlertListS = 196;//moving to state tooManyAlerts
		_goto_MonitorAlertListS(_info);
		}
		else if ((_occurredEvent(_event,378/*addAlert*/))){
		noOfAlerts ++;

		_state_id_MonitorAlertListS = 198;//moving to state newAlert
		_goto_MonitorAlertListS(_info);
		}
}
else if (_state_id_MonitorAlertListS==196){
		if (1==0){}
		else if ((_occurredEvent(_event,378/*addAlert*/))){
		noOfAlerts ++;
_cls_AlertSys0.pw .println ("Too many alerts already");

		_state_id_MonitorAlertListS = 196;//moving to state tooManyAlerts
		_goto_MonitorAlertListS(_info);
		}
		else if ((_occurredEvent(_event,380/*delAllAlerts*/)) && (noOfAlerts >6 )){
		noOfAlerts --;

		_state_id_MonitorAlertListS = 196;//moving to state tooManyAlerts
		_goto_MonitorAlertListS(_info);
		}
		else if ((_occurredEvent(_event,380/*delAllAlerts*/)) && (noOfAlerts ==6 )){
		noOfAlerts --;

		_state_id_MonitorAlertListS = 198;//moving to state newAlert
		_goto_MonitorAlertListS(_info);
		}
}
}

public void _goto_MonitorAlertListS(String _info){
_cls_AlertSys0.pw.println("[MonitorAlertListS]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + _string_MonitorAlertListS(_state_id_MonitorAlertListS, 1));
_cls_AlertSys0.pw.flush();
}

public String _string_MonitorAlertListS(int _state_id, int _mode){
switch(_state_id){
case 198: if (_mode == 0) return "newAlert"; else return "newAlert";
case 196: if (_mode == 0) return "tooManyAlerts"; else return "!!!SYSTEM REACHED BAD STATE!!! tooManyAlerts "+new _BadStateExceptionAlertSys().toString()+" ";
case 197: if (_mode == 0) return "badDelete"; else return "!!!SYSTEM REACHED BAD STATE!!! badDelete "+new _BadStateExceptionAlertSys().toString()+" ";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}