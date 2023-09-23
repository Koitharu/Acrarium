/*
 * (C) Copyright 2023 Lukas Morawietz (https://github.com/F43nd1r)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.faendir.acra

import com.faendir.acra.persistence.user.Role
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder

fun withAuth(vararg authorities: GrantedAuthority, block: () -> Unit) {
    val previous = SecurityContextHolder.getContext().authentication
    SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(null, null, authorities.toList())
    try {
        block()
    } finally {
        SecurityContextHolder.getContext().authentication = previous
    }
}