#!/bin/bash

_DIR="/home/sistach/Documents/git/Jugant/Bash/FullPack kiruku"
_SUB_DIR="4 5 6"
_FILTER=aux

_cddevelop() {
  local cur=${COMP_WORDS[COMP_CWORD]}
  case $COMP_CWORD in
    1) COMPREPLY=( $(compgen -W "$_DIR" -- "$cur") ); return 0 ;;
    2)
      COMPREPLY=()
      dir=${COMP_WORDS[1]}
      for i in $(cd $dir && compgen -d -- "$cur" | grep -v $_FILTER); do
        if [ -d "$dir/$i/4" ] && [ -d "$dir/$i/5" ]; then ## TODO more like _SUB_DIR
          COMPREPLY+=($i)
        fi
      done
    ;;
    *)
      COMPREPLY=()
  esac
}

complete -F _cddevelop m
