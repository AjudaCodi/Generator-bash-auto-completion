#!/bin/bash

_standard_completion() {
  echo ${1} | cut -d'|' -f2
}

files_pwd_generic=/home/sistach/Documents/git/Jugant/Bash/generatorCompletion/like/
