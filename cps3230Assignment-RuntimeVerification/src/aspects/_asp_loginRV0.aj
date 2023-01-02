package aspects;

import larva.*;
public aspect _asp_loginRV0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_loginRV0.initialize();
}
}
before ( boolean inAlert) : (call(* *.inAlertPage(..)) && args(inAlert) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_loginRV0.lock){

_cls_loginRV0 _cls_inst = _cls_loginRV0._get_cls_loginRV0_inst();
_cls_inst.inAlert = inAlert;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 364/*inAlertScreen*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 364/*inAlertScreen*/);
}
}
before () : (call(* *.goodLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_loginRV0.lock){

_cls_loginRV0 _cls_inst = _cls_loginRV0._get_cls_loginRV0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 360/*validLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 360/*validLogin*/);
}
}
before () : (call(* *.badLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_loginRV0.lock){

_cls_loginRV0 _cls_inst = _cls_loginRV0._get_cls_loginRV0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 358/*invalidLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 358/*invalidLogin*/);
}
}
before ( boolean inLogin) : (call(* *.inLoginPage(..)) && args(inLogin) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_loginRV0.lock){

_cls_loginRV0 _cls_inst = _cls_loginRV0._get_cls_loginRV0_inst();
_cls_inst.inLogin = inLogin;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 362/*inLoginScreen*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 362/*inLoginScreen*/);
}
}
}