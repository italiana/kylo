/**
 *
 */
package com.thinkbiganalytics.metadata.api.sla;

/*-
 * #%L
 * thinkbig-metadata-api
 * %%
 * Copyright (C) 2017 ThinkBig Analytics
 * %%
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
 * #L%
 */

import org.quartz.CronExpression;

import java.beans.Transient;
import java.text.ParseException;

/**
 *
 */
public class DatasourceUpdatedSinceSchedule extends DependentDatasource {

    private transient CronExpression cronExpression;
    private String cronString;

    public DatasourceUpdatedSinceSchedule() {
    }

    public DatasourceUpdatedSinceSchedule(String datasetName, String cronStr) throws ParseException {
        super(datasetName);
        this.cronExpression = new CronExpression(cronStr);
        this.cronString = cronStr;
    }

    public DatasourceUpdatedSinceSchedule(String datasetName, CronExpression cronExpression) throws ParseException {
        super(datasetName);
        this.cronExpression = cronExpression;
        this.cronString = cronExpression.toString();
    }

    public CronExpression getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(CronExpression cronExpression) {
        this.cronExpression = cronExpression;
        this.cronString = cronExpression.toString();
    }

    @Override
    @Transient
    public String getDescription() {
        return "Datasource " + getDatasourceName() + " has been updated since " + getCronExpression();
    }

    protected String getCronString() {
        return cronString;
    }

    protected void setCronString(String cronString) {
        this.cronString = cronString;
        try {
            this.cronExpression = new CronExpression(cronString);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
