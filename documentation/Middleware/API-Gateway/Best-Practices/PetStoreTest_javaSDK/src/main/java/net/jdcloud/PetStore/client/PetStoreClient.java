/*
 * Copyright 2018 JDCLOUD.COM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http:#www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * apiName1
 * petstore api
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the jdcloud code generator program.
 */
package net.jdcloud.PetStore.client;

import com.jdcloud.sdk.JdcloudSdkException;
import com.jdcloud.sdk.auth.CredentialsProvider;
import com.jdcloud.sdk.client.Environment;
import com.jdcloud.sdk.client.Jdcloud;
import com.jdcloud.sdk.client.JdcloudClient;
import com.jdcloud.sdk.client.JdcloudValidateException;
import com.jdcloud.sdk.http.HttpRequestConfig;
import net.jdcloud.PetStore.model.*;

/**
 * https://xv3xbwah945y.cn-north-1.jdcloud-api.net/petstore
 */
public class PetStoreClient extends JdcloudClient {

    public final static String ApiVersion = "";
    private final static String UserAgentPrefix = "JdcloudSdkJava";
    public final static String ClientVersion = "2.0.0";
    public final static String DefaultEndpoint = "xv3xbwah945y.cn-north-1.jdcloud-api.net";
    public final static String ServiceName = "xv3xbwah945y";
    public final static String UserAgent = UserAgentPrefix + "/" + ClientVersion + " " + ServiceName + "/" + ApiVersion;
    public final static String REGION_ID = "cn-north-1";

    CredentialsProvider credentialsProvider;
    HttpRequestConfig httpRequestConfig;
    Environment environment = new Environment.Builder().endpoint(DefaultEndpoint).build();

    public CredentialsProvider getCredentialsProvider() {
        return credentialsProvider;
    }

    public HttpRequestConfig getHttpRequestConfig() {
        return httpRequestConfig;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public String getUserAgent() {
        return UserAgent;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public String getVersion() {
        return ApiVersion;
    }
    
    public String getRegionId() {
        return REGION_ID;
    }

    private PetStoreClient() {

    }

    public static Builder builder() {
        return new DefaultBuilder();
    }
    
    public interface Builder {
        public Builder credentialsProvider(CredentialsProvider provider);
        public Builder environment(Environment environment);
        public Builder httpRequestConfig(HttpRequestConfig config);
        public PetStoreClient build() throws JdcloudSdkException;
    }

    private static class DefaultBuilder implements Builder {

        private PetStoreClient PetStoreClient;

        public DefaultBuilder() {
            PetStoreClient = new PetStoreClient();
        }

        public DefaultBuilder credentialsProvider(CredentialsProvider provider) {
            PetStoreClient.credentialsProvider = provider;
            return this;
        }

        public DefaultBuilder httpRequestConfig(HttpRequestConfig config) {
            PetStoreClient.httpRequestConfig = config;
            return this;
        }

        public PetStoreClient build() throws JdcloudSdkException {

            if (PetStoreClient.credentialsProvider == null) {
                //if credentialsProvider not set, try load jdcloud global default  credentials provider
                PetStoreClient.credentialsProvider = Jdcloud.defaultInstance().getCredentialsProvider();
                if (PetStoreClient.credentialsProvider == null) {
                    throw new JdcloudValidateException("PetStoreClient build error: jdcloud credentials provider not set");
                }
            }
            if (PetStoreClient.httpRequestConfig == null) {
                PetStoreClient.httpRequestConfig = Jdcloud.defaultInstance().getHttpRequestConfig();
                if (PetStoreClient.httpRequestConfig == null) {
                    throw new JdcloudValidateException("PetStoreClient build error: http request config not set");
                }
            }
            return PetStoreClient;
        }

        public Builder environment(Environment environment) {
            PetStoreClient.environment = environment;
            return this;
        }
    }
    
    
    /**
     * petstore api
     *
     * @param request
     * @return
     * @throws JdcloudSdkException
     */
    public ApiName0Response apiName0(ApiName0Request request) throws JdcloudSdkException {
        return new ApiName0Executor().client(this).execute(request);
    }

    
    /**
     * petstore api
     *
     * @param request
     * @return
     * @throws JdcloudSdkException
     */
    public ApiName1Response apiName1(ApiName1Request request) throws JdcloudSdkException {
        return new ApiName1Executor().client(this).execute(request);
    }

    
}
