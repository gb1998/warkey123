package cn.ac.iscas.gz.result;

public class DataResult<T> extends Result {
    private  T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
