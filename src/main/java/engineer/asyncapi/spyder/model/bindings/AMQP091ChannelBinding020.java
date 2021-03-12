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
public interface AMQP091ChannelBinding020 extends AMQP091ChannelBinding {

	String getIs();

	String getBindingVersion();

	String getExchangeName();

	String getExchangeType();

	String getExchangeVHost();

	String getQueueName();

	String getQueueVHost();

	boolean isExchangeAutoDelete();

	boolean isExchangeDurable();

	boolean isQueueAutoDelete();

	boolean isQueueDurable();

	boolean isQueueExclusive();

	boolean isRoutingKey();

}