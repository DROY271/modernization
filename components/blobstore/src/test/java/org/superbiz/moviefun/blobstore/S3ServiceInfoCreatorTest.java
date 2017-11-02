package org.superbiz.moviefun.blobstore;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cloud.cloudfoundry.CloudFoundryConnector;
import org.springframework.cloud.util.EnvironmentAccessor;

import java.lang.reflect.Method;

import static org.mockito.Mock.*;
import static org.mockito.Mockito.when;

public class S3ServiceInfoCreatorTest {

    CloudFoundryConnector testConnector = new CloudFoundryConnector();

    @Mock
    EnvironmentAccessor mockEnvironment;

    @Before
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
        Method m = CloudFoundryConnector.class.getDeclaredMethod("setCloudEnvironment", EnvironmentAccessor.class);
        m.setAccessible(true);
        m.invoke(testConnector, mockEnvironment);
    }

    @Test
    public void testS3Parsing() {
        when(mockEnvironment.getEnvValue("VCAP_SERVICES")).thenReturn(" {\n" +
                "  \"aws-s3\": [\n" +
                "   {\n" +
                "    \"credentials\": {\n" +
                "     \"access_key_id\": \"AKIAJ2RRGS4CL6H5WJVA\",\n" +
                "     \"bucket\": \"cf-f1044774-1943-4045-8749-673a2743cfb3\",\n" +
                "     \"region\": \"ap-southeast-1\",\n" +
                "     \"secret_access_key\": \"7ATUncy033duGyoZbe3Y6ricuCQ2PMh5Sfkdr1IU\"\n" +
                "    },\n" +
                "    \"label\": \"aws-s3\",\n" +
                "    \"name\": \"moviefun-s3\",\n" +
                "    \"plan\": \"standard\",\n" +
                "    \"provider\": null,\n" +
                "    \"syslog_drain_url\": null,\n" +
                "    \"tags\": [],\n" +
                "    \"volume_mounts\": []\n" +
                "   }\n" +
                "  ],\n" +
                "  \"p-mysql\": [\n" +
                "   {\n" +
                "    \"credentials\": {\n" +
                "     \"hostname\": \"10.0.16.78\",\n" +
                "     \"jdbcUrl\": \"jdbc:mysql://10.0.16.78:3306/cf_ee601ab4_0328_45b2_a50e_68e9b1d99715?user=HNUoa0vi7yPcdWw0\\u0026password=0YP34ev2WwGFlZY0\",\n" +
                "     \"name\": \"cf_ee601ab4_0328_45b2_a50e_68e9b1d99715\",\n" +
                "     \"password\": \"0YP34ev2WwGFlZY0\",\n" +
                "     \"port\": 3306,\n" +
                "     \"uri\": \"mysql://HNUoa0vi7yPcdWw0:0YP34ev2WwGFlZY0@10.0.16.78:3306/cf_ee601ab4_0328_45b2_a50e_68e9b1d99715?reconnect=true\",\n" +
                "     \"username\": \"HNUoa0vi7yPcdWw0\"\n" +
                "    },\n" +
                "    \"label\": \"p-mysql\",\n" +
                "    \"name\": \"album-database\",\n" +
                "    \"plan\": \"100mb\",\n" +
                "    \"provider\": null,\n" +
                "    \"syslog_drain_url\": null,\n" +
                "    \"tags\": [\n" +
                "     \"mysql\",\n" +
                "     \"relational\"\n" +
                "    ],\n" +
                "    \"volume_mounts\": []\n" +
                "   }\n" +
                "  ]\n" +
                "}");
        System.out.println(testConnector.getServiceInfos());
    }


}
