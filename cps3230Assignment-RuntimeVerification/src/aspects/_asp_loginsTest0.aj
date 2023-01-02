package aspects;

import larva.*;
public aspect _asp_loginsTest0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_loginsTest0.initialize();
}
}
before ( boolean inAlert) : (call(* *.inAlertPage(..)) && args(inAlert) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_loginsTest0.lock){

_cls_loginsTest0 _cls_inst = _cls_loginsTest0._get_cls_loginsTest0_inst();
_cls_inst.inAlert = inAlert;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 174/*inAlertScreen*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 174/*inAlertScreen*/);
}
}
before () : (call(* *.goodLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_loginsTest0.lock){

_cls_loginsTest0 _cls_inst = _cls_loginsTest0._get_cls_loginsTest0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 170/*validLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 170/*validLogin*/);
}
}
before () : (call(* *.badLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_loginsTest0.lock){

_cls_loginsTest0 _cls_inst = _cls_loginsTest0._get_cls_loginsTest0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 168/*invalidLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 168/*invalidLogin*/);
}
}
before ( boolean inLogin) : (call(* *.inLoginPage(..)) && args(inLogin) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_loginsTest0.lock){

_cls_loginsTest0 _cls_inst = _cls_loginsTest0._get_cls_loginsTest0_inst();
_cls_inst.inLogin = inLogin;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 172/*inLoginScreen*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 172/*inLoginScreen*/);
}
}
}