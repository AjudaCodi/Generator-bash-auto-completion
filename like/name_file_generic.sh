#!/bin/bash

_generic_name() {
  local arguments_number="${1}"
  if [ "$arguments_number" -eq 1 ]; then
    echo "Usage: name | version status opcioArbre opcioFitxers rar |"; return 0
  fi

  local cur=$2
  local next_arguments="${@:3}"

  case ${cur} in
    (opcioArbre) _generic_name_opcioArbre ${arguments_number} ${next_arguments[@]} ;;
    (opcioFitxers) _generic_name_opcioFitxers ${arguments_number} ${next_arguments[@]} ;;
    (rar) _generic_name_rar ${arguments_number} ${next_arguments[@]} ;;
    (*)
      echo "Usage: name > version status opcioArbre opcioFitxers rar <|"; return 0
      ;;
  esac
  echo ""; return 0
}

_generic_name_opcioArbre() {
  local arguments_number="${1}"
  if [ "$arguments_number" -eq 2 ]; then
    echo "Usage: name opcioArbre | branch1 branch2 |"; return 0
  fi

  local cur=$2
  local next_arguments="${@:3}"
  case ${cur} in
    (branch1) _generic_name_opcioArbre_branch1 ${arguments_number} ${next_arguments[@]} ;;
    (branch2) _generic_name_opcioArbre_branch2 ${arguments_number} ${next_arguments[@]} ;;
    (*)
      echo "Usage: name opcioArbre > branch1 branch2 <|"; return 0
      ;;
  esac
}

_generic_name_opcioArbre_branch1() {
  local arguments_number="${1}"
  if [ "$arguments_number" -eq 3 ]; then
    echo "Usage: name opcioArbre branch1 | fulla0 fulla2 |"; return 0
  fi
}

_generic_name_opcioArbre_branch2() {
  local arguments_number="${1}"
  if [ "$arguments_number" -eq 3 ]; then
    echo "Usage: name opcioArbre branch2 | fulla1 fulla3 |"; return 0
  fi
}

_generic_name_opcioFitxers() {
  local arguments_number="${1}"
  if [ "$arguments_number" -eq 2 ]; then
    echo "Usage: name opcioFitxers | Paquets Text All |"; return 0
  fi

  local cur=$2
  local next_arguments="${@:3}"
  case ${cur} in
    (Paquets) _generic_name_opcioFitxers_Paquets ${arguments_number} ${next_arguments[@]} ;;
    (Text) _generic_name_opcioFitxers_Text ${arguments_number} ${next_arguments[@]} ;;
    (All) _generic_name_opcioFitxers_All ${arguments_number} ${next_arguments[@]} ;;
    (*)
      echo "Usage: name opcioFitxers > Paquets Text All <|"; return 0
      ;;
  esac
}

_generic_name_opcioFitxers_Paquets() {
  local arguments_number="${1}"
  if [ "$arguments_number" -eq 3 ]; then
    echo "Usage: name opcioFitxers Paquets |" *.txp "|"; return 0
  fi
}

_generic_name_opcioFitxers_Text() {
  local arguments_number="${1}"
  if [ "$arguments_number" -eq 3 ]; then
    echo "Usage: name opcioFitxers Paquets |" *.txt "|"; return 0
  fi
}

_generic_name_opcioFitxers_All() {
  local arguments_number="${1}"
  if [ "$arguments_number" -eq 3 ]; then
    echo "Usage: name opcioFitxers Paquets |" * "|"; return 0
  fi
}

_generic_name_rar() {
  local arguments_number="${1}"
  if [ "$arguments_number" -eq 2 ]; then
    echo "Usage: name rar | de |"; return 0
  fi

  local cur=$2
  local next_arguments="${@:3}"
  case ${cur} in
    (de) _generic_name_rar_de ${arguments_number} ${next_arguments[@]} ;;
    (*)
      echo "Usage: name rar > de <|"
      ;;
  esac
}

_generic_name_rar_de() {
  local arguments_number="${1}"
  if [ "$arguments_number" -eq 3 ]; then
    echo "Usage: name rar de | collons |"; return 0
  fi

  local cur=$2
  local next_arguments="${@:3}"
  case ${cur} in
    (collons) _generic_name_rar_de_collons ${arguments_number} ${next_arguments[@]} ;;
    (*)
      echo "Usage: name rar de > collons <|"
      ;;
  esac
}

_generic_name_rar_de_collons() {
  local arguments_number="${1}"
  if [ "$arguments_number" -eq 4 ]; then
    echo "Usage: name rar de collons | opcio1 opcio2 opcio3 |"; return 0
  fi
}
