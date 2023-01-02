package larva;


import main.AlertSystem;
import main.Alerts;


import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_finialfsm0 implements _callable{

public static PrintWriter pw; 
public static _cls_finialfsm0 root;

public static LinkedHashMap<_cls_finialfsm0,_cls_finialfsm0> _cls_finialfsm0_instances = new LinkedHashMap<_cls_finialfsm0,_cls_finialfsm0>();
static{
try{
RunningClock.start();
pw = new PrintWriter("C:\\Users\\User\\workspace\\testrv/src/output_finialfsm.txt");

root = new _cls_finialfsm0();
_cls_finialfsm0_instances.put(root, root);
  root.initialisation();
}catch(Exception ex)
{ex.printStackTrace();}
}

_cls_finialfsm0 parent; //to remain null - this class does not have a parent!
public static boolean inLogin;
public static boolean inAlert;
int no_automata = 1;
 public int noOfAlerts =0 ;
 public int invalidLogins =0 ;

public static void initialize(){}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_finialfsm0() {
}

public void initialisation() {
}

public static _cls_finialfsm0 _get_cls_finialfsm0_inst() { synchronized(_cls_finialfsm0_instances){
 return root;
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_finialfsm0))
{return true;}
else
{return false;}
}

public int hashCode() {
return 0;
}

public void _call(String _info, int... _event){
synchronized(_cls_finialfsm0_instances){
_performLogic_LoginAttempt(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_finialfsm0[] a = new _cls_finialfsm0[1];
synchronized(_cls_finialfsm0_instances){
a = _cls_finialfsm0_instances.keySet().toArray(a);}
for (_cls_finialfsm0 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_finialfsm0_instances){
_cls_finialfsm0_instances.remove(this);}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}

int _state_id_LoginAttempt = 213;

public void _performLogic_LoginAttempt(String _info, int... _event) {

_cls_finialfsm0.pw.println("[LoginAttempt]AUTOMATON::> LoginAttempt("+") STATE::>"+ _string_LoginAttempt(_state_id_LoginAttempt, 0));
_cls_finialfsm0.pw.flush();

if (0==1){}
else if (_state_id_LoginAttempt==213){
		if (1==0){}
		else if ((_occurredEvent(_event,410/*inLoginScreen*/)) && (inLogin ==false )){
		_cls_finialfsm0.pw .println ("Invalid Login. Back in login screen, try again!");

		_state_id_LoginAttempt = 213;//moving to state LOGGEDOUT
		_goto_LoginAttempt(_info);
		}
		else if ((_occurredEvent(_event,408/*validLogin*/)) && (inLogin ==true )){
		_cls_finialfsm0.pw .println ("Valid Login event. @ Home Page");

		_state_id_LoginAttempt = 208;//moving to state LOGGEDIN
		_goto_LoginAttempt(_info);
		}
}
else if (_state_id_LoginAttempt==208){
		if (1==0){}
		else if ((_occurredEvent(_event,412/*inAlertScreen*/)) && (inAlert ==true )){
		_cls_finialfsm0.pw .println (" @ Alert Page");

		_state_id_LoginAttempt = 209;//moving to state VIEWALERTS
		_goto_LoginAttempt(_info);
		}
		else if ((_occurredEvent(_event,410/*inLoginScreen*/)) && (inLogin ==true )){
		_cls_finialfsm0.pw .println ("Valid Logout");

		_state_id_LoginAttempt = 213;//moving to state LOGGEDOUT
		_goto_LoginAttempt(_info);
		}
}
else if (_state_id_LoginAttempt==212){
		if (1==0){}
		else if ((_occurredEvent(_event,416/*delAllAlerts*/)) && (noOfAlerts ==0 )){
		_cls_finialfsm0.pw .println ("Bad Delete!!!");

		_state_id_LoginAttempt = 211;//moving to state badDelete
		_goto_LoginAttempt(_info);
		}
		else if ((_occurredEvent(_event,414/*addAlert*/)) && (noOfAlerts ==5 )){
		noOfAlerts ++;

		_state_id_LoginAttempt = 210;//moving to state tooManyAlerts
		_goto_LoginAttempt(_info);
		}
		else if ((_occurredEvent(_event,414/*addAlert*/))){
		noOfAlerts ++;

		_state_id_LoginAttempt = 209;//moving to state VIEWALERTS
		_goto_LoginAttempt(_info);
		}
}
else if (_state_id_LoginAttempt==209){
		if (1==0){}
		else if ((_occurredEvent(_event,414/*addAlert*/))){
		noOfAlerts ++;
_cls_finialfsm0.pw .println ("Too many alerts already");

		_state_id_LoginAttempt = 210;//moving to state tooManyAlerts
		_goto_LoginAttempt(_info);
		}
		else if ((_occurredEvent(_event,412/*inAlertScreen*/)) && (inLogin ==true )){
		_cls_finialfsm0.pw .println ("Valid Logout");

		_state_id_LoginAttempt = 213;//moving to state LOGGEDOUT
		_goto_LoginAttempt(_info);
		}
}
else if (_state_id_LoginAttempt==210){
		if (1==0){}
		else if ((_occurredEvent(_event,416/*delAllAlerts*/)) && (noOfAlerts >6 )){
		noOfAlerts --;

		_state_id_LoginAttempt = 210;//moving to state tooManyAlerts
		_goto_LoginAttempt(_info);
		}
		else if ((_occurredEvent(_event,416/*delAllAlerts*/)) && (noOfAlerts ==6 )){
		noOfAlerts --;

		_state_id_LoginAttempt = 212;//moving to state newAlert
		_goto_LoginAttempt(_info);
		}
}
}

public void _goto_LoginAttempt(String _info){
_cls_finialfsm0.pw.println("[LoginAttempt]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + _string_LoginAttempt(_state_id_LoginAttempt, 1));
_cls_finialfsm0.pw.flush();
}

public String _string_LoginAttempt(int _state_id, int _mode){
switch(_state_id){
case 213: if (_mode == 0) return "LOGGEDOUT"; else return "LOGGEDOUT";
case 208: if (_mode == 0) return "LOGGEDIN"; else return "LOGGEDIN";
case 212: if (_mode == 0) return "newAlert"; else return "newAlert";
case 209: if (_mode == 0) return "VIEWALERTS"; else return "VIEWALERTS";
case 210: if (_mode == 0) return "tooManyAlerts"; else return "tooManyAlerts";
case 211: if (_mode == 0) return "badDelete"; else return "badDelete";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}