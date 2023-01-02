package aspects;

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
_cls_inst._call(thisJoinPoint.getSignature().toString(), 396/*inAlertScreen*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 396/*inAlertScreen*/);
}
}
before () : (call(* *.goodLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_finialfsm0.lock){

_cls_finialfsm0 _cls_inst = _cls_finialfsm0._get_cls_finialfsm0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 392/*validLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 392/*validLogin*/);
}
}
before () : (call(* *.badLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_finialfsm0.lock){

_cls_finialfsm0 _cls_inst = _cls_finialfsm0._get_cls_finialfsm0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 390/*invalidLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 390/*invalidLogin*/);
}
}
before ( boolean inLogin) : (call(* *.inLoginPage(..)) && args(inLogin) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_finialfsm0.lock){

_cls_finialfsm0 _cls_inst = _cls_finialfsm0._get_cls_finialfsm0_inst();
_cls_inst.inLogin = inLogin;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 394/*inLoginScreen*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 394/*inLoginScreen*/);
}
}
}