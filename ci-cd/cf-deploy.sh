#!/usr/bin/env bash
#
curl -L "https://packages.cloudfoundry.org/stable?release=linux64-binary&source=github" | tar -zx
mv cf /usr/local/bin
./cf --version
./cf login -a https://api.run.pivotal.io -u akku.util@gmail.com -p Hr32*sapient -s development
./cf push