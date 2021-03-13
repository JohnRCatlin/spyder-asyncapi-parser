/* ------------------------------------------------------------------
Copyright 2021 asyncapi.engineer

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
------------------------------------------------------------------ */
package engineer.asyncapi.spyder.model.bindings;

/**
 * 
 * @author johncatlin
 *
 */
public enum BindingType {

	AMQP("amqp"),
	AMQP1("amqp1"),
	HTTP("http"),
	IBMMQ("ibmmq"),
	JMS("jms"),
	KAFKA("kafka"),
	MERCURE("mercure"),
	MQTT("mqtt"),
	MQTT5("mqtt5"),
	NATS("nats"),
	REDIS("redis"),
	SNS("sns"),
	SQS("sqs"),
	STOMP("stomp"),
	UNDEFINE("undefine"),
	WEBSOCKETS("websockets");

	public static final BindingType getType(final String value) {
		return (null == value) ? BindingType.UNDEFINE : BindingType.valueOf(value.toUpperCase());
	}

	public final String value;

	BindingType(final String value) {
		this.value = value;
	}
}
