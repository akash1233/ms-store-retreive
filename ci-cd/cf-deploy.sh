#!/usr/bin/env bash
#
#curl -L \"https://cli.run.pivotal.io/stable?release=linux64-binary\" | tar -zx
./cf --version
./cf login -a https://api.run.pivotal.io -u dharmendra.singh4@cognizant.com -p Hr32*sapient -s development
./cf push