#!/bin/bash

source /home/sistach/Documents/git/Jugant/Bash/generatorCompletion/like/utils.sh

_completion_name() {
  local cur=${COMP_WORDS[COMP_CWORD]}
  local output="$(${files_pwd_generic}./name_file_generic.sh "${COMP_CWORD}" ${COMP_WORDS[@]:1})"
  COMPREPLY=( $(compgen -W "$(_standard_completion "${output}")" -- "${cur}") )
}

complete -F _completion_name m
