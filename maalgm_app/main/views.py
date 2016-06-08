from django.shortcuts import redirect, render

from maalgm_app.settings import GRPC_IP, GRPC_PORT
from session.views import _is_logged_in
import json
import session_client

# Create your views here.


def main_page(request):
    dark_theme = True
    url_list = []
    if _is_logged_in(request):
        result = session_client.get_url_list(request.session.get('sessionId'), GRPC_IP, GRPC_PORT)
        if result.status == '200':
            url_list = json.loads(result.urlList)
    return render(request, 'main/main_page.html', {'url_list': url_list,
                                                   'username': _is_logged_in(request),
                                                   'dark_theme': dark_theme})

def feed(request):
    url = request.GET.get('url', '')
    feed_list = {}
    url_list = []
    error = ''
    if _is_logged_in(request):
        result = session_client.get_feed_list(request.session.get('sessionId'), url, GRPC_IP, GRPC_PORT)
        if result.status == '200':
            feed_list = json.loads(result.feedList)
        else:
            error = 'invalid url'
        result = session_client.get_url_list(request.session.get('sessionId'), GRPC_IP, GRPC_PORT)
        if result.status == '200':
            url_list = json.loads(result.urlList)
    else:
        return redirect('/')
    return render(request, 'main/feed_page.html', {'feed_list': feed_list,
                                                   'url_list': url_list,
                                                   'username': _is_logged_in(request),
                                                   'error': error})

def add_url(request):
    if not _is_logged_in(request):
        return redirect('/')

    feed_list = {}
    error = ''
    if request.method == 'POST':
        url = request.POST.get('url', '')
        result = session_client.insert_url(request.session.get('sessionId'), url, GRPC_IP, GRPC_PORT)
        if result.status == '200':
            return redirect('/feed/?url=' + url)
    return redirect('/')
