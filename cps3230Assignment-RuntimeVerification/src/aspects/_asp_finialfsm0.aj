package aspects;

import main.AlertSystem;
import main.Alerts;


import larva.*;
public aspect _asp_finialfsm0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_finialfsm0.initialize();
}
}
before ( boolean inAlert) : (call(* *.inAlertPage(..)) && args(inAlert) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_finialfsm0.lock){

_cls_finialfsm0 _cls_inst = _cls_finialfsm0._get_cls_finialfsm0_inst();
_cls_inst.inAlert = inAlert;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 412/*inAlertScreen*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 412/*inAlertScreen*/);
}
}
before () : (call(* *.deleteAllAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_finialfsm0.lock){

_cls_finialfsm0 _cls_inst = _cls_finialfsm0._get_cls_finialfsm0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 416/*delAllAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 416/*delAllAlerts*/);
}
}
before () : (call(* *.goodLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_finialfsm0.lock){

_cls_finialfsm0 _cls_inst = _cls_finialfsm0._get_cls_finialfsm0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 408/*validLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 408/*validLogin*/);
}
}
before () : (call(* *.addAlert(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_finialfsm0.lock){

_cls_finialfsm0 _cls_inst = _cls_finialfsm0._get_cls_finialfsm0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 414/*addAlert*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 414/*addAlert*/);
}
}
before () : (call(* *.badLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_finialfsm0.lock){

_cls_finialfsm0 _cls_inst = _cls_finialfsm0._get_cls_finialfsm0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 406/*invalidLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 406/*invalidLogin*/);
}
}
before ( boolean inLogin) : (call(* *.inLoginPage(..)) && args(inLogin) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_finialfsm0.lock){

_cls_finialfsm0 _cls_inst = _cls_finialfsm0._get_cls_finialfsm0_inst();
_cls_inst.inLogin = inLogin;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 410/*inLoginScreen*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 410/*inLoginScreen*/);
}
}
}