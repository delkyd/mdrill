/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alimama.quanjingmonitor.kmeans;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Mapper;


public class KMeansGroupMapper extends Mapper<WritableComparable<?>, Text, Text, Cluster> {

  ParseVector parse=new ParseVector();

  @Override
  protected void map(WritableComparable<?> key, Text point, Context context)
    throws IOException, InterruptedException {

	  	String line=point.toString();
		Vector pointv=parse.parseVector(line);
		pointv.setNumPoints(1);
		
		Cluster clu=new Cluster(pointv,0);
		context.write(new Text(String.valueOf(parse.parseKey(line))),clu);
	
  }
  
  


	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		super.setup(context);

		Configuration conf = context.getConfiguration();
		parse.setup(conf);

	}

	
}
