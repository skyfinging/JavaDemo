package demo.util;

import lombok.Data;

@Data
public class ServerResponse {
    private int status;
    private String msg;

    public static ServerResponse success(String token){
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setStatus(0);
        serverResponse.setMsg(token);
        return serverResponse;
    }
}
