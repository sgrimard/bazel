// Copyright 2023 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package com.google.devtools.build.lib.bazel.bzlmod;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import net.starlark.java.eval.StarlarkInt;

/**
 * Builder for a {@link RepoSpec} object that indicates how to materialize a repo corresponding to
 * an {@code git_repository} repo rule call.
 */
public class GitRepoSpecBuilder {
  private final ImmutableMap.Builder<String, Object> attrBuilder;

  public GitRepoSpecBuilder() {
    attrBuilder = new ImmutableMap.Builder<>();
  }

  @CanIgnoreReturnValue
  public GitRepoSpecBuilder setRepoName(String repoName) {
    attrBuilder.put("name", repoName);
    return this;
  }

  @CanIgnoreReturnValue
  public GitRepoSpecBuilder setRemote(String remote) {
    attrBuilder.put("remote", remote);
    return this;
  }

  @CanIgnoreReturnValue
  public GitRepoSpecBuilder setBranch(String branch) {
    attrBuilder.put("branch", branch);
    return this;
  }

  @CanIgnoreReturnValue
  public GitRepoSpecBuilder setCommit(String commit) {
    attrBuilder.put("commit", commit);
    return this;
  }

  @CanIgnoreReturnValue
  public GitRepoSpecBuilder setTag(String tag) {
    attrBuilder.put("tag", tag);
    return this;
  }

  @CanIgnoreReturnValue
  public GitRepoSpecBuilder setStripPrefix(String stripPrefix) {
    attrBuilder.put("strip_prefix", stripPrefix);
    return this;
  }

  @CanIgnoreReturnValue
  public GitRepoSpecBuilder setPatches(ImmutableList<String> patches) {
    attrBuilder.put("patches", patches);
    return this;
  }

  @CanIgnoreReturnValue
  public GitRepoSpecBuilder setPatchCmds(ImmutableList<String> patchCmds) {
    attrBuilder.put("patch_cmds", patchCmds);
    return this;
  }

  @CanIgnoreReturnValue
  public GitRepoSpecBuilder setPatchStrip(int patchStrip) {
    attrBuilder.put("patch_args", ImmutableList.of("-p" + patchStrip));
    return this;
  }

  @CanIgnoreReturnValue
  public GitRepoSpecBuilder setArchiveType(String archiveType) {
    if (!Strings.isNullOrEmpty(archiveType)) {
      attrBuilder.put("type", archiveType);
    }
    return this;
  }

  public RepoSpec build() {
    return RepoSpec.builder()
        .setBzlFile("@bazel_tools//tools/build_defs/repo:git.bzl")
        .setRuleClassName("git_repository")
        .setAttributes(AttributeValues.create(attrBuilder.buildOrThrow()))
        .build();
  }
}
