function _generic_name {
	local argumentsNumber="${1}"
	# TODO Discutir si treure condicional!
	if [ "$argumentsNumber" -eq 1 ]; then
		echo "Usage: name | version status opcioArbre opcioFitxers rar |"; return 0
	fi

	local cur=$2
	local nextArguments="${@:2}"

	case ${cur} in
	(version)
		echo ""; return 0
		;;
	(status)
		echo ""; return 0
		;;
	(opcioArbre)
		;;
	(opcioFitxers)
		;;
	(rar)
		;;
	(*)
		echo "Usage: name | version status opcioArbre opcioFitxers rar |"; return 0
		;;
	esac
	echo ""; return 0
}
