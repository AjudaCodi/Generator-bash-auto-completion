#!/bin/bash

.  name_file_generic.sh

_standard_completion() {
  echo ${1} | cut -d'|' -f2
}

_completion_name() {
  local cur=${COMP_WORDS[COMP_CWORD]}
  local output="$(_generic_name "${COMP_CWORD}" ${COMP_WORDS[@]:1})"
  COMPREPLY=( $(compgen -W "$(_standard_completion "${output}")" -- "${cur}") )
}

complete -F _completion_name m
