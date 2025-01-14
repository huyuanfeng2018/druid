/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.druid.server.security;

import com.google.common.base.Strings;
import org.apache.druid.java.util.common.StringUtils;

public class Access
{
  static final String DEFAULT_ERROR_MESSAGE = "Unauthorized";

  public static final Access OK = new Access(true);
  public static final Access DENIED = new Access(false);

  private final boolean allowed;
  private final String message;

  public Access(boolean allowed)
  {
    this(allowed, "");
  }

  public Access(boolean allowed, String message)
  {
    this.allowed = allowed;
    this.message = message;
  }

  public boolean isAllowed()
  {
    return allowed;
  }

  public String getMessage()
  {
    return message;
  }

  public String toMessage()
  {
    if (!Strings.isNullOrEmpty(message)) {
      return toString();
    } else if (allowed) {
      return "Authorized";
    } else {
      return DEFAULT_ERROR_MESSAGE;
    }
  }

  @Override
  public String toString()
  {
    return StringUtils.format("Allowed:%s, Message:%s", allowed, message);
  }
}
