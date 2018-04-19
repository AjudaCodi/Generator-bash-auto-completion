#!/bin/bash

.  name_file_generic.sh

function _standard_completion {
	echo ${1} | cut -d'|' -f2
}

function _completion_name {
	local cur=${COMP_WORDS[COMP_CWORD]}
	local output="$(_generic_name "${COMP_CWORD}" ${COMP_WORDS[@]:2})"
	COMPREPLY=( $(compgen -W "$(_standard_completion "${output}")" -- "${cur}") )
}

complete -F _completion_name m
