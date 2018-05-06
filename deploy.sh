#!/bin/bash
if [ "$TRAVIS_BRANCH" == "development" ]; then
  eval "$(ssh-agent -s)" #start the ssh agent
  chmod 600 .travis/deploy.key # this key should have push access
  ssh-add .travis/deploy.key
  ssh-keyscan ns377672.ip-37-59-60.eu >> ~/.ssh/known_hosts
  git remote add deploy dokku@ns377672.ip-37-59-60.eu:famivac-gestionnaire-app-int
  git config --global push.default simple
  git push deploy development:master
fi
