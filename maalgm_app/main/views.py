from django.shortcuts import render

from maalgm_app.settings import GRPC_IP, GRPC_PORT
from session.views import _is_logged_in
import json
import session_client

# Create your views here.


def main_page(request):
    dark_theme = True
    url_list = []
    if _is_logged_in(request):
        url_json = get_url_list(request.session.get('sessionId'), GRPC_IP, GRPC_PORT)
        if url_json:
            url_list = json.loads(url_json)
    return render(request, 'main/main_page.html', {'url_list': url_list,
                                                   'dark_theme': dark_theme})
