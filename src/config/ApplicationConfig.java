package config;

public class ApplicationConfig {

    public static String[] anonymousUriList = {
      "/members/join",
      "/members/login"
    };

    public static String[] hasAuthUriList = {
        "/members/logout"
    }

    private boolean isAnonymous(String url){

        String[] anonymousUrlList = ApplicationConfig.anonymousUriList;

        for(String regUrl : anonymousUrlList){
            if(regUrl.equals(url)){
                return true;
            }
        }
        return false;
    }

    private boolean isNeedAuth(String url){

        String[] hasAuthUrlList = ApplicationConfig.hasAuthUriList;

        for(String regUrl : hasAuthUrlList){
            if(regUrl.equals(url)){
                return true;
            }
        }
        return false;
    }


}
