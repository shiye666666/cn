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

package net.jdcloud.PetStore.model;

import com.jdcloud.sdk.service.JdcloudRequest;
import com.jdcloud.sdk.annotation.*;
import java.util.Objects;
import java.util.Arrays;
import net.jdcloud.PetStore.model.ReqBody;
import java.io.Serializable;
import javax.xml.bind.annotation.*;

/**
 * ApiName1Request
 */
@XmlRootElement(name = "ApiName1Request")
@XmlAccessorType(XmlAccessType.FIELD)

public class ApiName1Request extends JdcloudRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "reqBody")
    @BodyParameter
    private ReqBody reqBody = null;

    public ApiName1Request reqBody(ReqBody reqBody) {
        this.reqBody = reqBody;
        return this;
    }

    /**
     * 
     * @return reqBody
    **/

    public ReqBody getReqBody() {
        return reqBody;
    }

    public void setReqBody(ReqBody reqBody) {
        this.reqBody = reqBody;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApiName1Request apiName1Request = (ApiName1Request) o;
        return Objects.equals(this.reqBody, apiName1Request.reqBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reqBody);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ApiName1Request {\n");
        
        sb.append("    reqBody: ").append(toIndentedString(reqBody)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
          return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}

