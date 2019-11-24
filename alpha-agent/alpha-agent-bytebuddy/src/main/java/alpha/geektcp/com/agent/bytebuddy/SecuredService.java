//package alpha.geektcp.com.agent.bytebuddy;
//
///**
// * @author tanghaiyang on 2019/11/24 17:02.
// */
//public class SecuredService extends Service {
//    @Override
//    void deleteEverything() {
//        if(UserHolder.user.equals("ADMIN")) {
//            super.deleteEverything();
//        } else {
//            throw new IllegalStateException("Not authorized");
//        }
//    }
//}