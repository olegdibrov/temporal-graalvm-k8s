{{- define "cc.name" -}}control-center{{- end -}}
{{- define "cc.fullname" -}}{{ printf "%s-%s" .Release.Name (include "cc.name" .) | trunc 63 | trimSuffix "-" -}}{{- end -}}
{{- define "cc.labels" -}}
app.kubernetes.io/name: {{ include "cc.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end -}}