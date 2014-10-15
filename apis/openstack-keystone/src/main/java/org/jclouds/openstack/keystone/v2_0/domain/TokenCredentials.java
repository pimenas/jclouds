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
package org.jclouds.openstack.keystone.v2_0.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.beans.ConstructorProperties;

import org.jclouds.openstack.keystone.v2_0.config.CredentialType;
import org.jclouds.openstack.keystone.v2_0.config.CredentialTypes;

import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Token Credentials
 */
@CredentialType(CredentialTypes.TOKEN_CREDENTIALS)
public class TokenCredentials {

   public static Builder<?> builder() {
      return new ConcreteBuilder();
   }

   public Builder<?> toBuilder() {
      return new ConcreteBuilder().fromTokenCredentials(this);
   }

   public static TokenCredentials createWithToken(String id) {
      return new TokenCredentials(id);
   }

   public abstract static class Builder<T extends Builder<T>>  {
      protected abstract T self();

      protected String id;

      /**
       * @see TokenCredentials#getId()
       */
      public T id(String id) {
         this.id = id;
         return self();
      }

      public TokenCredentials build() {
         return new TokenCredentials(id);
      }

      public T fromTokenCredentials(TokenCredentials in) {
         return this.id(in.getId());
      }
   }

   private static class ConcreteBuilder extends Builder<ConcreteBuilder> {
      @Override
      protected ConcreteBuilder self() {
         return this;
      }
   }

   private final String id;

   @ConstructorProperties({ "token" })
   protected TokenCredentials(String id) {
      this.id = checkNotNull(id, "id");
   }

   /**
    * @return the token
    */
   public String getId() {
      return this.id;
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(id);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      TokenCredentials that = TokenCredentials.class.cast(obj);
      return Objects.equal(this.id, that.id);
   }

   protected ToStringHelper string() {
      return MoreObjects.toStringHelper(this).omitNullValues().add("id", id);
   }

   @Override
   public String toString() {
      return string().toString();
   }

}
