/*
 * Copyright 2016 Bartosz Lipinski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bartoszlipinski.trigger;

public abstract class Trigger {

    public static abstract class WithParam {

        public static class WithResult<Param, Result> extends TriggerBase<Param, Result> {

            public static abstract class OnTriggered<Param, Result> implements Trigger.OnTriggered<Param, Result> {
                @Override
                public final Result onInnerTriggered(Param param) {
                    return onTriggered(param);
                }

                public abstract Result onTriggered(Param param);
            }

            protected WithResult(OnTriggered<Param, Result> onTriggered) {
                super(onTriggered);
            }

            public static <Param, Result> WithResult<Param, Result> create(OnTriggered<Param, Result> onTriggered) {
                return new WithResult<>(onTriggered);
            }

            public final Result pull(Param param) {
                return innerPull(param);
            }
        }

        public static class NoResult<Param> extends TriggerBase<Param, Void> {

            public static abstract class OnTriggered<Param> implements Trigger.OnTriggered<Param, Void> {
                @Override
                public final Void onInnerTriggered(Param param) {
                    onTriggered(param);
                    return null;
                }

                public abstract void onTriggered(Param param);
            }

            protected NoResult(OnTriggered<Param> onTriggered) {
                super(onTriggered);
            }

            public static <Param> NoResult<Param> create(OnTriggered<Param> onTriggered) {
                return new NoResult<>(onTriggered);
            }

            public final void pull(Param param) {
                innerPull(param);
            }
        }
    }

    public static abstract class NoParam {

        public static class WithResult<Result> extends TriggerBase<Void, Result> {

            public static abstract class OnTriggered<Result> implements Trigger.OnTriggered<Void, Result> {
                @Override
                public final Result onInnerTriggered(Void param) {
                    return onTriggered();
                }

                public abstract Result onTriggered();
            }

            protected WithResult(OnTriggered<Result> onTriggered) {
                super(onTriggered);
            }

            public static <Result> WithResult<Result> create(OnTriggered<Result> onTriggered) {
                return new WithResult<>(onTriggered);
            }

            public final Result pull() {
                return innerPull(null);
            }
        }

        public static class NoResult extends TriggerBase<Void, Void> {

            public static abstract class OnTriggered implements Trigger.OnTriggered<Void, Void> {
                @Override
                public final Void onInnerTriggered(Void param) {
                    onTriggered();
                    return null;
                }

                public abstract void onTriggered();
            }

            protected NoResult(OnTriggered onTriggered) {
                super(onTriggered);
            }

            public static NoResult create(OnTriggered onTriggered) {
                return new NoResult(onTriggered);
            }

            public final void pull() {
                innerPull(null);
            }
        }
    }

    private interface OnTriggered<Param, Result> {
        Result onInnerTriggered(Param param);
    }

    private abstract static class TriggerBase <Param, Result> {

        private final OnTriggered<Param, Result> onTriggered;

        protected TriggerBase(OnTriggered<Param, Result> onTriggered) {
            this.onTriggered = onTriggered;
        }

        protected final Result innerPull(Param param) {
            return onTriggered.onInnerTriggered(param);
        }
    }

    // Suppress default constructor for noninstantiability
    private Trigger() {
        throw new AssertionError();
    }
}
