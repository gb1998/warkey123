package cn.ac.iscas.gz.util;

import cn.ac.iscas.gz.enums.ResultTypeEnum;
import cn.ac.iscas.gz.result.DataResult;

/**
 * 封装结果集
 */
public class ResultUtil {


   public static <T> DataResult<T> success(T data){
       DataResult<T> dataResult=new DataResult<>();
       dataResult.setCode(ResultTypeEnum.succeess.getCode());
       dataResult.setMessage("success");
       dataResult.setData(data);
       return dataResult;

   }


}
