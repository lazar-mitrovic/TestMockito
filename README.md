# Mockito Native Image bug reproducer

```bash
./generateMetadata.sh
./gradlew nativeTest
```
That fails during the image run time with:
```
MethodSource [className = 'org.example.MainTest', methodName = 'mockitoTest', methodParameterTypes = '']
    => com.oracle.svm.core.jdk.UnsupportedFeatureError: Proxy class defined by interfaces [interface org.mockito.plugins.MockMaker] not found. Generating proxy classes at runtime is not supported. Proxy classes need to be defined at image build time by specifying the list of interfaces that they implement. To define proxy classes use -H:DynamicProxyConfigurationFiles=<comma-separated-config-files> and -H:DynamicProxyConfigurationResources=<comma-separated-config-resources> options.
```

Next, add the following to the end of  `src/test/resources/META-INF/native-image/proxy-config.json`
```json
,
{
    "interfaces":["org.mockito.plugins.MockMaker"]
}
```
After running `./gradlew nativeTest` it will now fail with the:
```
     Caused by: com.oracle.svm.core.jdk.UnsupportedFeatureError: Defining a class from new bytecodes at run time is not supported. Class net.bytebuddy.utility.Invoker$Dispatcher with hash b4371c0b5187b914976c7db687153600bcd974e028cbe432ed804c9b9f84776a was not provided during the image build. Please see BuildConfiguration.md.
```
