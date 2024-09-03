package interfaces;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public interface BaseAPI {
   String  BASE_URL = "https://ilcarro-backend.herokuapp.com";
   String REGISTRATION_URL = "/v1/user/registration/usernamepassword";

   Gson GSON = new Gson();
   MediaType JSON = MediaType.get("application/json");
   OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();
}
