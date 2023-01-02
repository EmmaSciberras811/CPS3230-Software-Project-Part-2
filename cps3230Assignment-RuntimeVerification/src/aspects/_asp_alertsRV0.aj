package aspects;

import larva.*;
public aspect _asp_alertsRV0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_alertsRV0.initialize();
}
}
before () : (call(* *.goodLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_alertsRV0.lock){

_cls_alertsRV0 _cls_inst = _cls_alertsRV0._get_cls_alertsRV0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 208/*goodLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 208/*goodLogin*/);
}
}
before () : (call(* *.badLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_alertsRV0.lock){

_cls_alertsRV0 _cls_inst = _cls_alertsRV0._get_cls_alertsRV0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 206/*badLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 206/*badLogin*/);
}
}
before ( boolean locked) : (call(* *.setLocked(..)) && args(locked) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_alertsRV0.lock){

_cls_alertsRV0 _cls_inst = _cls_alertsRV0._get_cls_alertsRV0_inst();
_cls_inst.locked = locked;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 210/*unlockAccount*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 210/*unlockAccount*/);
}
}
}