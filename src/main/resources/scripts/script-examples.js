/*
 This is details the typical scripting options that might be used
 */
logger.debug('Logs a message to the driver logs');

// This loads the helper functions into the current script engine scope
load('classpath:scripts/lib.js');

// Create the message object to be returned
var message = {};

// Set the standard message properties
// This shows multiple ways a property might be retrieved and set.
message.vnfdId = 'xyz-xyz-xyz-xyz';
message.vnfInstanceName = executionRequest.getLifecycleName();
message.vnfInstanceDescription = executionRequest.getProperties().get('description');

// Shows how the library function (addProperty) can be used to populate parameters based on dot notation
message.additionalParams = {};
message.additionalParams['vnfPkgId'] = '123-123-123-123';
addProperty(message.additionalParams, 'test.dot.notation', 'bob');
addProperty(message.additionalParams, 'check.for.0.arrays', 'firstarrayvalue');
addProperty(message.additionalParams, 'check.for.2.arrays', 'thirdarrayvalue');
addProperty(message.additionalParams, 'check.that.arrays.0', 'arrayvalue1');
addProperty(message.additionalParams, 'check.that.arrays.1', 'arrayvalue2');

// Loops through all properties passed in
for (var key in executionRequest.getProperties()) {
    print('Got property [' + key + '], value = [' + executionRequest.properties[key] + ']');
}

logger.debug('Message generated successfully');

// Turn the message object into a JSON string to be returned back to the Java driver
// MUST always end with this (for message creation)
JSON.stringify(message);