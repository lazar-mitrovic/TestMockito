#!/bin/bash
METADATA_DIR="src/test/resources/META-INF/native-image"

rm -rdf $METADATA_DIR
./gradlew test -Pagent
./gradlew metadataCopy --task test --dir $METADATA_DIR
