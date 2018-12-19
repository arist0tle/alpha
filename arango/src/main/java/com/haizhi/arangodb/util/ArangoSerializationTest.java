/*
 * DISCLAIMER
 *
 * Copyright 2016 ArangoDB GmbH, Cologne, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright holder is ArangoDB GmbH, Cologne, Germany
 */

package com.haizhi.arangodb.util;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import com.arangodb.util.ArangoSerialization;
import com.arangodb.util.ArangoSerializer;
import org.junit.BeforeClass;
import org.junit.Test;

import com.arangodb.ArangoDB;
import com.arangodb.entity.BaseDocument;
import com.arangodb.internal.ArangoExecutorSync;
import com.arangodb.internal.InternalArangoDB;
import com.arangodb.internal.velocystream.internal.ConnectionSync;
import com.arangodb.velocypack.Type;
import com.arangodb.velocypack.VPackBuilder;
import com.arangodb.velocypack.VPackSlice;
import com.arangodb.velocypack.ValueType;
import com.arangodb.velocystream.Response;

/**
 * @author Mark - mark at arangodb.com
 *
 */
public class ArangoSerializationTest {

	private static ArangoSerialization util;

	@BeforeClass
	public static void setup() {
		final InternalArangoDB<ArangoExecutorSync, Response, ConnectionSync> arangoDB = new ArangoDB.Builder().build();
		util = arangoDB.util();
	}

	@Test
	public void deseriarlize() {
		final VPackBuilder builder = new VPackBuilder().add(ValueType.OBJECT).add("foo", "bar").close();
		final BaseDocument doc = util.deserialize(builder.slice(), BaseDocument.class);
		assertThat(doc.getAttribute("foo").toString(), is("bar"));
	}

	@Test
	public void serialize() {
		final BaseDocument entity = new BaseDocument();
		entity.addAttribute("foo", "bar");
		final VPackSlice vpack = util.serialize(entity);
		assertThat(vpack.get("foo").isString(), is(true));
		assertThat(vpack.get("foo").getAsString(), is("bar"));
	}

	@Test
	public void serializeNullValues() {
		final BaseDocument entity = new BaseDocument();
		entity.addAttribute("foo", null);
		final VPackSlice vpack = util.serialize(entity, new ArangoSerializer.Options().serializeNullValues(true));
		assertThat(vpack.get("foo").isNull(), is(true));
	}

	@Test
	public void serializeType() {
		final Collection<BaseDocument> list = new ArrayList<BaseDocument>();
		list.add(new BaseDocument());
		list.add(new BaseDocument());

		final VPackSlice vpack = util.serialize(list,
			new ArangoSerializer.Options().type(new Type<Collection<BaseDocument>>() {
			}.getType()));
		assertThat(vpack.isArray(), is(true));
		assertThat(vpack.getLength(), is(list.size()));
	}

}
