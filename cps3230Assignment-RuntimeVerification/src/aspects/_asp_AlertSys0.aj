package aspects;

import main.AlertSystem;
import main.Alerts;


import larva.*;
public aspect _asp_AlertSys0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_AlertSys0.initialize();
}
}
before () : (call(* *.deleteAllAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_AlertSys0.lock){

_cls_AlertSys0 _cls_inst = _cls_AlertSys0._get_cls_AlertSys0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 380/*delAllAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 380/*delAllAlerts*/);
}
}
before () : (call(* *.addAlert(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_AlertSys0.lock){

_cls_AlertSys0 _cls_inst = _cls_AlertSys0._get_cls_AlertSys0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 378/*addAlert*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 378/*addAlert*/);
}
}
}