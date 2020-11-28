#!/bin/bash

mvn assembly:assembly -DdescriptorId=jar-with-dependencies

exeFolder="projectRefsExe"

if [[ ! -d "$exeFolder" ]]; then
	mkdir "$exeFolder"
	echo "[INFO] $exeFolder dir was created"	
else
	rm -r "$exeFolder"
	mkdir "$exeFolder"
	echo "[INFO] $exeFolder dir was re-created"
fi

cp -r resources2 $exeFolder/resources

echo "[INFO] Adding some examples"

cp target/projectRefs*-jar-with-dependencies.jar $exeFolder/projectRefs.jar

zip -r "$exeFolder.zip" "$exeFolder" > /dev/null
echo "[INFO] Create $exeFolder.zip"