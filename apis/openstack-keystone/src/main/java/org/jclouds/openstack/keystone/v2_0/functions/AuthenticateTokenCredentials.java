/*
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
package org.jclouds.openstack.keystone.v2_0.functions;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jclouds.openstack.keystone.v2_0.AuthenticationApi;
import org.jclouds.openstack.keystone.v2_0.config.CredentialType;
import org.jclouds.openstack.keystone.v2_0.config.CredentialTypes;
import org.jclouds.openstack.keystone.v2_0.domain.Access;
import org.jclouds.openstack.keystone.v2_0.domain.TokenCredentials;
import org.jclouds.openstack.keystone.v2_0.functions.internal.BaseAuthenticator;

import com.google.common.base.Optional;

@CredentialType(CredentialTypes.TOKEN_CREDENTIALS)
@Singleton
public class AuthenticateTokenCredentials extends BaseAuthenticator<TokenCredentials> {
   protected final AuthenticationApi api;

   @Inject
   public AuthenticateTokenCredentials(AuthenticationApi api) {
      this.api = api;
   }

   @Override
   protected Access authenticateWithTenantName(Optional<String> tenantName, TokenCredentials tokenCredentials) {
      return api.authenticateWithTenantNameAndCredentials(tenantName.orNull(), tokenCredentials);
   }

   @Override
   protected Access authenticateWithTenantId(Optional<String> tenantId, TokenCredentials tokenCredentials) {
      return api.authenticateWithTenantIdAndCredentials(tenantId.orNull(), tokenCredentials);
   }

   @Override
   public TokenCredentials createCredentials(String identity, String credential) {
      return TokenCredentials.createWithToken(credential);
   }

   @Override
   public String toString() {
      return "authenticateTokenCredentials()";
   }
}
