import os
import re


def count(p, s, files_to_read=[], files_read=-1):
    wordCounter = 0
    files_read += 1

    with open(p, "r") as f:
        content = f.read()

    input_directives = re.findall(r"\\input{([^}]*)}", content)
    files_to_read.extend(input_directives)

    for line in content.split("\n"):
        for word in line.split(" "):
            if word == s:
                wordCounter += 1

    if len(files_to_read) != files_read:
        pid = os.fork()

        if pid > 0:
            status = os.wait()
            if os.WIFEXITED(status[1]):
                return wordCounter + os.WEXITSTATUS(status[1])

        else:
            os._exit(count(files_to_read[files_read],
                           s, files_to_read, files_read))

    else:
        return wordCounter


print(count("plikA.txt", "super"))
