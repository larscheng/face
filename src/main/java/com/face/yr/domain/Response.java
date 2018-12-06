package com.face.yr.domain;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/12/6 13:37
 */
public class Response {

    private Result result;
    private long log_id;
    private String error_msg;
    private int cached;
    private int error_code;
    private long timestamp;

    public Result getResult() {
        return result;
    }

    public Response setResult(Result result) {
        this.result = result;
        return this;
    }

    public long getLog_id() {
        return log_id;
    }

    public Response setLog_id(long log_id) {
        this.log_id = log_id;
        return this;
    }

    public String getError_msg() {
        return error_msg;
    }

    public Response setError_msg(String error_msg) {
        this.error_msg = error_msg;
        return this;
    }

    public int getCached() {
        return cached;
    }

    public Response setCached(int cached) {
        this.cached = cached;
        return this;
    }

    public int getError_code() {
        return error_code;
    }

    public Response setError_code(int error_code) {
        this.error_code = error_code;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Response setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public String toString() {
        return "Response{" +
                "result=" + result +
                ", log_id=" + log_id +
                ", error_msg='" + error_msg + '\'' +
                ", cached=" + cached +
                ", error_code=" + error_code +
                ", timestamp=" + timestamp +
                '}';
    }
}
