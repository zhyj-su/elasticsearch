/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.license;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.action.RestToXContentListener;
import org.elasticsearch.xpack.core.XPackClient;
import org.elasticsearch.xpack.core.rest.XPackRestHandler;

import static org.elasticsearch.rest.RestRequest.Method.GET;

public class RestGetBasicStatus extends XPackRestHandler {

    RestGetBasicStatus(Settings settings, RestController controller) {
        super(settings);
        controller.registerHandler(GET, URI_BASE + "/license/basic_status", this);
    }

    @Override
    protected RestChannelConsumer doPrepareRequest(RestRequest request, XPackClient client) {
        return channel -> client.licensing().prepareGetStartBasic().execute(new RestToXContentListener<>(channel));
    }

    @Override
    public String getName() {
        return "xpack_basic_status_action";
    }
}
