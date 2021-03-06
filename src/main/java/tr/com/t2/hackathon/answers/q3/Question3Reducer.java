/*
 * Copyright (c) 2014, "SkyKeeper Team". All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY 
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 */

package tr.com.t2.hackathon.answers.q3;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;

import tr.com.t2.hackathon.answers.Answers.BaseReducer;

/**
 * @author Serkan OZAL
 */
public class Question3Reducer extends BaseReducer<LongWritable, IntWritable, NullWritable, IntWritable> {

    private int userCount = 0;
	
    protected void reduce(LongWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        try {
        	// Each key represents a unique user
        	userCount++;
        }
        catch (Throwable t) {
            logger.error("Error occured while executing reduce function of Reducer", t);
        }    
    }
    
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        // Emit partial tweet count on clean up to only one redurer by using constant key (NullWritable)
        context.write(NullWritable.get(), new IntWritable(userCount));
    }

}
