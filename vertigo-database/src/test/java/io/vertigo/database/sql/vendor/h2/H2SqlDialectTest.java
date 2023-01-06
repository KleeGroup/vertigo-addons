/**
 * vertigo - application development platform
 *
 * Copyright (C) 2013-2023, Vertigo.io, team@vertigo.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.vertigo.database.sql.vendor.h2;

import java.util.Optional;

import io.vertigo.database.impl.sql.vendor.h2.H2DataBase;
import io.vertigo.database.sql.AbstractSqlDialectTest;
import io.vertigo.database.sql.vendor.SqlDialect;

/**
 *
 * @author mlaroche
 */
public final class H2SqlDialectTest extends AbstractSqlDialectTest {

	@Override
	public SqlDialect getDialect() {
		return new H2DataBase().getSqlDialect();

	}

	@Override
	public String getExpectedInsertQuery() {
		return "insert into MOVIE (ID, TITLE) values (nextval('SEQ_MOVIE'),  #dto.title#);";
	}

	@Override
	public String getExpectedSelectForUpdateWildCardQuery() {
		return " select * from MOVIE where ID = #id# for update ";
	}

	@Override
	public String getExpectedSelectForUpdateFieldsQuery() {
		return " select ID, TITLE from MOVIE where ID = #id# for update ";
	}

	@Override
	public Optional<String> getExpectedCreatePrimaryKeyQuery() {
		return Optional.empty();
	}

	@Override
	public String getExpectedAppendMaxRowsQuery() {
		return "select * from MOVIE limit 100";
	}

	@Override
	public String getExpectedAppendSkipRowsQuery() {
		return "select * from MOVIE offset 10";
	}

	@Override
	public String getExpectedAppendSortQuery() {
		return "select * from MOVIE order by TITLE";
	}

	@Override
	public String getExpectedAppendSortDescQuery() {
		return "select * from MOVIE order by TITLE desc";
	}
}
