package com.learn.flink.wordcount;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.typeutils.TupleTypeInfo;
import org.apache.flink.api.java.utils.ParameterTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordCount
{
    private static final Logger logger =  LoggerFactory.getLogger("WordCount");
    public static void main(String[] args)
        throws Exception
    {

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        ParameterTool params = ParameterTool.fromArgs(args);

        env.getConfig().setGlobalJobParameters(params);

        DataSet<String> text = env.readTextFile(params.get("input"));

        DataSet<String> filtered = text.filter( value -> value.startsWith("N"));

        //DataSet<Tuple2<String, Integer>> tokenized = filtered.map(new Tokenizer());

        DataSet<Tuple2<String, Integer>> tokenized = filtered.map(value -> new Tuple2(value, Integer.valueOf(1)))
            .returns(new TupleTypeInfo(TypeInformation.of(String.class), TypeInformation.of(Integer.class)));


        DataSet<Tuple2<String, Integer>> counts = tokenized.groupBy(0).sum(1);
        if (params.has("output"))
        {
            counts.writeAsCsv(params.get("output"), "\n", " ");

            env.execute("WordCount Example"); // Starts the execution
        }
    }

//    public static final class Tokenizer
//        implements MapFunction<String, Tuple2<String, Integer>>
//    {
//        public Tuple2<String, Integer> map(String value)
//        {
//            return new Tuple2(value, Integer.valueOf(1));
//        }
//    }
}
